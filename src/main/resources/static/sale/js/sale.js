document.getElementById('refreshPage').addEventListener('click', function() {
    window.location.reload();
});

/**
 * @name      : [eventListener] onLoad 함수
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-26
 */
document.addEventListener('DOMContentLoaded', function() {


    /** ■■■■■>>  고객 정보 전화번호 textBox */
    const phoneNumberTbx = document.getElementById('tbx_custTelNumber');

    /* 고객 정보 전화번호 tbx 포맷팅 Event */
    phoneNumberTbx.addEventListener('input', autoFormatPhoneNumber);

    /* 고객 정보 전화번호 tbx keyPrss Event */
    phoneNumberTbx.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            e.preventDefault();
            validateAndCheckCustomer();
        }
    });

    /** ■■■■■>>  단말 번호 입력 박스 */
    const serialNumberInput = document.getElementById('serialNumber');
    /* 단말 번호 입력 입력 박스 keyPrss Event */
    serialNumberInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            e.preventDefault();
            fetchDeviceInventory();
        }
    });

    /* 단말 모델 전체 조회 */
    fetchDeviceInfos();

    /* 요금제 전체 조회 */
    fetchPlanInfos();

});

/**
 * @name      : 전화번호 유효성 검사 및 고객 확인
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-26
 */
async function validateAndCheckCustomer() {

    let phoneNumber = document.getElementById('tbx_custTelNumber').value.replace(/-/g, '');
    if (phoneNumber.length < 11) {
        alert('전화번호는 총 11자리입니다.');
        return;
    }

    /* 고객 정보 조회 */
    await checkCustomer(phoneNumber);

}


/**
 * @name      : 전화번호 tbx 포맷팅 함수
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-28
 */
function autoFormatPhoneNumber(event) {
    let value = event.target.value.replace(/[^0-9]/g, ''); // 숫자만 추출
    let formattedNumber = '';

    /* 자동 하이픈(-) 추가 */
    if (value.length > 3 && value.length <= 7) {
        formattedNumber = `${value.slice(0, 3)}-${value.slice(3)}`;
    } else if (value.length > 7) {
        formattedNumber = `${value.slice(0, 3)}-${value.slice(3, 7)}-${value.slice(7, 11)}`;
    } else {
        formattedNumber = value;
    }

    /* 포맷된 번호로 값 업데이트 */
    event.target.value = formattedNumber;
}


/**
 * @name      : 고객정보조회 api
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-28
 */
async function checkCustomer() {
    try {
        let phoneNumber = document.getElementById('tbx_custTelNumber').value.replace(/-/g, '');
        var res = false;
        const response = await $.ajax({
            url: '/api/v1/sales/checkCustomer',
            type: 'GET',
            data: { phone: phoneNumber },
        });

        console.log("고객정보 : ", response);

        if(response) {
            document.getElementById('customerIDInput').value = response.subscriptionId;
            document.getElementById('marketCodeInput').value = response.marketCode;
            document.getElementById('phoneNumberInput').value = response.subscriptionPhone;
            alert('고객 확인 완료');
            return true;
        } else {
            alert('존재하지 않는 고객입니다.');
            return false;
        }
    } catch (error) {
        console.error("에러 발생:", error);
        return false;
    }
}

/**
 * @name      : 단말 모델 전체 조회
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-03-01
 */
let allDeviceInfos = [];
async function fetchDeviceInfos() {
    try {
        const response = await fetch('/api/v1/device/deviceinfo/alldeviceinfo');

        if (!response.ok) {
            throw new Error('디바이스 정보를 가져오는 데 실패했습니다.');
        }

        const data = await response.json();
        allDeviceInfos = data; /* 받아온 모든 디바이스 정보를 전역 변수에 저장 */
        console.log("디바이스 정보:", allDeviceInfos);

        const deviceModelSelect = document.getElementById('deviceModel');
        deviceModelSelect.innerHTML = '';

        data.forEach(device => {
            const option = document.createElement('option');
            option.value = device.deviceCode;
            option.text = device.deviceName;
            deviceModelSelect.appendChild(option);
        });

    } catch (error) {
        console.error('디바이스 정보 조회 실패:', error);
        alert('디바이스 정보를 조회할 수 없습니다.');
    }
}

/**
 * @name      : 단말기 정보 조회 by 단말모델코드, 단말번호
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-26
 */
function fetchDeviceInventory() {

    let deviceCode = document.getElementById('deviceModel').value;
    let deviceNumber = document.getElementById('serialNumber').value;

    /* 단말기 유효성 체크 */
    if (deviceCode == null || deviceCode === '') {
        alert('단말기 모델을 선택해주세요.');
        return;
    }

    if (deviceNumber == null || deviceNumber === '') {
        alert('단말기 번호를 입력하세요.');
        return;
    }
    deviceNumber = deviceNumber.padStart(20, '0');

    $.ajax({
        url: '/api/v1/device/deviceinventory/device-inventory',
        type: 'GET',
        data: {
            deviceCode: deviceCode,
            deviceNumber: deviceNumber
        },
        success: function(response) {
            alert("단말기 선택 완료");

            console.log('선택 단말 정보 :', response);

        },
        error: function(xhr, status, error) {
            console.error('단말기 정보 조회 실패:', error);
            alert('단말기 정보 조회에 실패했습니다.');
        }
    });
}


let selectedPlanCode;
function selectPlan() {

    var planName = document.getElementById('plan').value;
    var selectedPlanInfo = allPlanInfos.find(plan => plan.planCode === planName);

    selectedPlanCode = selectedPlanInfo.planCode;
    console.log("요금제 정보 : " + selectedPlanInfo);
    console.log("요금제 코드 : " + selectedPlanCode);
    
    if(selectedPlanInfo)
        alert('요금제 선택 완료');
    else
        alert('요금제를 선택하세요.');


    /* 다음 섹션 보이기
    document.getElementById('selectPayment').style.display = 'block'; */
}

function selectSubsidy() {
    debugger;
    // 여기에 지원금 선택 처리 로직을 구현합니다.
    const subsidyType = $('input[name="subsidy"]:checked').val();
    var salesAmount = 0;
    var subsidyAmount = 0;
    var totalAmount = 0;

    var deviceCode = document.getElementById('deviceModel').value;
    var selectedDeviceInfo = allDeviceInfos.find(device => device.deviceCode === deviceCode);
    debugger;
    if(isNaN(selectedDeviceInfo.devicePrice))
        alert("단말기의 출고가격이 설정되어 있지 않습니다.");
    else
        totalAmount = selectedDeviceInfo.devicePrice;

    if (subsidyType === 'sufu') {
        const deviceCode = document.getElementById('deviceModel').value; // 단말기 코드 설정
        const planCode = selectedPlanCode; // 요금제 코드 설정
        const marketCode = document.getElementById('marketCodeInput').value; // 마켓 코드 설정 (쿼리 파라미터로 추가)

        $.ajax({
            url: `/api/v1/public/subsidydto/${deviceCode}/${planCode}`,
            method: 'GET',
            async: false,
            success: function (data) {
                const now = new Date();

                debugger;
                const validPolicies = data.filter(policy => {
                    const startDatetime = new Date(policy.startDatetime);
                    const endDatetime = new Date(policy.endDatetime);
                    return policy.marketCode === marketCode && startDatetime <= now && endDatetime >= now;
                });

                console.log("validPolicies : " + validPolicies); // 응답 데이터 처리
                subsidyAmount = data[0].supportAmount;
                // 예: 데이터를 화면에 표시하는 로직 추가
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error fetching data: ', textStatus, errorThrown);
            }
        });
    } else {
        console.log('무약정 선택됨 - API 호출 안 함');
        // 무약정 선택 시 수행할 작업
    }
    debugger;
    $('#totalAmount').val(totalAmount);
    $('#subsidyAmount').val(subsidyAmount);
    $('#salesAmount').val(totalAmount-subsidyAmount);
    $('#differenceAmount').val(totalAmount-subsidyAmount);
    alert("지원금 선택이 완료되었습니다.");
}

/**
 * @name      : 납입금액 계산
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function onPaymentAmountChange(inputValue) {
    // 입력값 가져오기
    var paymentAmount = parseFloat(inputValue);

    // 결제금액이 입력되었는지 확인

    if (!isNaN(paymentAmount)) {
        // 납입금액 계산 및 결과를 납입금액 필드에 입력
        var receivedAmount = paymentAmount;
        document.getElementById("receivedAmount").value = receivedAmount.toFixed(0); // 소수점 아래 절삭

        // 차액 계산 및 결과를 차액 필드에 입력
        var salesAmount = parseFloat(document.getElementById("salesAmount").value);
        var differenceAmount = salesAmount - receivedAmount;

        // 판매금액 - 납입금액이 0보다 작을 경우
        if (differenceAmount < 0) {
            alert("납입금액이 판매금액을 초과하였습니다.");

            // 납입금액, 차액, 결제금액을 0으로 설정
            document.getElementById("receivedAmount").value = "0";
            document.getElementById("differenceAmount").value = (salesAmount - receivedAmount).toFixed(0); // 차액을 판매금액 - 납입금액으로 설정
            document.getElementById("paymentAmount").value = "0";

            onPaymentAmountChange("0");
        } else {
            // 차액이 0보다 크거나 같으면 차액을 입력
            document.getElementById("differenceAmount").value = differenceAmount.toFixed(0); // 소수점 아래 절삭
        }
    } else{
        onPaymentAmountChange("0");
    }
}
/**
 * @name      : 결제금액 입력 필드에 숫자만 입력 가능하도록 설정
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function validatePaymentAmount(input) {
    debugger;
    const inputValue = input;

    // 정규식을 사용하여 숫자만 허용하도록 함
    const numericValue = inputValue.replace(/[^0-9]/g, '');
    document.getElementById("paymentAmount").value = numericValue;
}
/**
 * @name      : 금액필드 초기화
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function resetAmountFields() {
    const amountFields = document.querySelectorAll('#selectPayment input[type="text"]');
    amountFields.forEach(function(field) {
        field.value = '0';
    });
}


/**
 * @name      : 단말기 판매 onclick 이벤트 시 수행되는 함수
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-26
 */
function completeSale() {

    debugger;

    /** ■■■■■>> 데이터 세팅 */
    /* 가입Id, 단말코드, 단말번호, 요금제코드 세팅 */
    let subscriptionId = document.getElementById('customerIDInput').value;
    let deviceCode = document.getElementById('deviceModel').value;
    let deviceNumber = document.getElementById('serialNumber').value;
    let planCode = document.getElementById('plan').value;

    /* 단말 가격 세팅 */
    const selectedDeviceInfo = allDeviceInfos.find(device => device.deviceCode === deviceCode);
    let devicePrice = "";
    if (selectedDeviceInfo) {
        devicePrice =  parseInt(selectedDeviceInfo.devicePrice);
        console.log("선택된 디바이스의 가격:", devicePrice);
    } else {
        console.error("해당 deviceCode에 대한 디바이스 정보를 찾을 수 없습니다.");
    }

    /* 지원금액 , 판매금액 세팅 */
    let subsidyAmount = parseFloat(document.getElementById('subsidyAmount').value) || 0 ;
    let salesAmount = parseFloat(document.getElementById('salesAmount').value) || 0;


    /** ■■■■■>> validation */
    /* 고객 정보가 없을 경우 작업을 막고 메시지를 표시 */
    if (!validateCustomerInfo()) {
        return;
    }

    /* 단말기 모델코드와 일련번호가 설정되지 않은 경우 경고 메시지 표시 */
    if (!validateDeviceData()) {
        return;
    }

    /* 요금제가 선택되지 않은 경우 작업을 막고 메시지를 표시 */
    if (!validatePlanSelection()) {
        return;
    }

    /* 출고금액이 0원이거나 차액이 0원이 아닌 경우 작업을 막고 메시지를 표시 */
    if (!validateSaleAmount()) {
        return;
    }

    /** ■■■■■>> 판매 데이터 세팅 */
    const saleData = {
        subscriptionId: subscriptionId,
        deviceCode: deviceCode,
        deviceNumber: deviceNumber,
        planCode: planCode,
        devicePrice: devicePrice,
        supportAmount: subsidyAmount,
        saleAmount: salesAmount
    };

    /** ■■■■■>> 판매처리 */
    sendSaleDataToServer(saleData);

}

/* 요금제 전체 조회 */
async function fetchPlanInfos() {
    try {
        const planInfos = await $.ajax({
            url: '/api/v1/plcys/retrieveAll',
            type: 'GET',
            success: function (planInfos) {
                console.log("요금제 정보 조회 성공:", planInfos);
                const planSelect = document.getElementById('plan');
                planSelect.innerHTML = '<option value="">요금제 선택</option>';

                /* 조회된 요금제 정보를 selectBox에 세팅 */
                planInfos.forEach(function (planInfo) {
                    const option = new Option(planInfo.planName, planInfo.planCode);
                    planSelect.add(option);
                });
            },
            error: function (error) {
                console.error("요금제 정보 조회 실패:", error);
                alert('요금제 정보를 조회할 수 없습니다.');
            }
        });
    } catch (error) {
        console.error("요금제 정보 조회 실패:", error);
        alert('요금제 정보를 조회할 수 없습니다.');
    }
}

/**
 * @name      : 단말기 판매 처리 api 호출 후 처리 함수
 * @작성자     : 권유리
 * @작성일자   : 2024-03-03
 */
function sendSaleDataToServer(saleData) {
    fetch('/api/v1/sales/presales', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(saleData)
    })
        .then(response => {
            if (response.ok) {
                // API 호출 성공 시 응답 데이터를 JSON으로 파싱하여 처리
                return response.json();
            } else {
                // API 호출 실패 시 오류 메시지를 처리
                return response.text().then(errorMsg => {
                    throw new Error(`판매에 실패했습니다: ${errorMsg}`);
                });
            }
        })
        .then(data => {
            // API 응답 데이터를 확인하여 처리
            if (data.rsltCd === 'Y') {
                // 성공적으로 처리된 경우 메시지를 알림창으로 표시
                alert(data.rsltMsg);
            } else {
                // 처리 실패 시 메시지를 알림창으로 표시
                alert(`판매에 실패했습니다: ${data.rsltMsg}`);
            }
        })
        .catch(error => {
            // 오류 발생 시 콘솔에 오류 로그를 출력하고 알림창으로 오류 메시지 표시
            console.error('판매 요청 에러:', error);
            alert('판매 요청 중 에러가 발생했습니다: ' + error.message);
        });
}


let allPlanInfos = [];
/* 요금제 전체 조회 */
async function fetchPlanInfos() {
    try {
        const planInfos = await $.ajax({
            url: '/api/v1/plcys/retrieveAll',
            type: 'GET',
            success: function (planInfos) {
                console.log("요금제 정보 조회 성공:", planInfos);
                const planSelect = document.getElementById('plan');
                planSelect.innerHTML = '<option value="">요금제 선택</option>';
                allPlanInfos = planInfos;

                /* 조회된 요금제 정보를 selectBox에 세팅 */
                planInfos.forEach(function (planInfo) {
                    const option = new Option(planInfo.planName, planInfo.planCode);
                    planSelect.add(option);
                });
            },
            error: function (error) {
                console.error("요금제 정보 조회 실패:", error);
                alert('요금제 정보를 조회할 수 없습니다.');
            }
        });
    } catch (error) {
        console.error("요금제 정보 조회 실패:", error);
        alert('요금제 정보를 조회할 수 없습니다.');
    }
}

/**
 * @name      : 고객 정보 유효성 검사 함수
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-01
 */
function validateCustomerInfo() {
    let customerId = document.getElementById('customerIDInput').value;

    if (!customerId) {
        alert('고객 정보가 없습니다. 판매를 진행할 수 없습니다.');
        return false;
    }

    return true;
}

/**
 * @name      : 단말기 모델코드와 일련번호 유효성 검사 함수
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function validateDeviceData() {
    let deviceModelCode = document.getElementById('deviceModel').value;
    let serialNumber = document.getElementById('serialNumber').value;

    if (!deviceModelCode || !serialNumber) {
        alert('단말기 모델과 일련번호를 모두 입력해주세요.');
        return false;
    }

    return true;
}
/**
 * @name      : 요금제 선택 여부 유효성 검사 함수
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function validatePlanSelection() {
    let planCode = document.getElementById('plan').value;

    if (!planCode) {
        alert('요금제를 선택해주세요.');
        return false;
    }

    return true;
}
/**
 * @name      : 출고금액 및 차액 유효성 검사 함수
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function validateSaleAmount() {
    let totalAmount = parseFloat(document.getElementById('totalAmount').value) || 0;
    let differenceAmount = parseFloat(document.getElementById('differenceAmount').value) || 0;

    if (totalAmount === 0) {
        alert('출고금액이 0원이므로 판매가 불가능합니다. 단말기 또는 요금제를 확인해주세요.');
        return false;
    }

    if (differenceAmount !== 0) {
        alert('결제가 완료되지 않아 판매가 불가능합니다.');
        return false;
    }

    return true;
}

/**
 * @name      : 단말기 모델 선택 변경 시 이벤트 처리 함수
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function onDeviceModelChange() {
    // 단말기 모델 선택이 변경되었을 때, 금액 필드 초기화
    resetAmountFields();
}

/**
 * @name      : 요금제 선택 변경 시 이벤트 처리 함수
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function onPlanChange() {
    // 요금제 선택이 변경되었을 때, 금액 필드 초기화
    resetAmountFields();
}

/**
 * @name      : 지원금 라디오 버튼 변경 시 이벤트 처리 함수
 * @작성자     : 유안수
 * @수정자     :
 * @작성일자   : 2024-03-02
 */
function onSubsidyChange() {
    // 지원금 라디오 버튼이 변경되었을 때, 금액 필드 초기화
    resetAmountFields();
}