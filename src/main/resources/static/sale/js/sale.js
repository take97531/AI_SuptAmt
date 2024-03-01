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
        allDeviceInfos = data; // 받아온 모든 디바이스 정보를 전역 변수에 저장
        console.log("디바이스 정보:", allDeviceInfos);

        // selectBox에 선택 옵션들을 추가
        const deviceModelSelect = document.getElementById('deviceModel');
        deviceModelSelect.innerHTML = ''; // 기존 옵션들을 모두 지우고 다시 채우기 위해 innerHTML을 초기화
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
    if (!deviceCode || !deviceNumber) {
        deviceNumber = deviceNumber.padStart(20, '0');
        alert('단말기 모델과 일련번호를 모두 입력해주세요.');
        return;
    }

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
    // 요금제 선택 로직

    // 다음 섹션 보이기
    document.getElementById('selectPayment').style.display = 'block';
}

function selectSubsidy() {
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
        salesAmount = selectedDeviceInfo.devicePrice;

    if (subsidyType === 'sufu') {
        const deviceCode = document.getElementById('deviceModel').value; // 단말기 코드 설정
        const planCode = selectedPlanCode; // 요금제 코드 설정
        const marketCode = document.getElementById('marketCodeInput').value; // 마켓 코드 설정 (쿼리 파라미터로 추가)

        $.ajax({
            url: `/api/v1/public/subsidy/${deviceCode}/${planCode}`,
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
                subsidyAmount = validPolicies[0].supportAmount;
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
    $('#salesAmount').val(salesAmount);
    $('#subsidyAmount').val(subsidyAmount);
    $('#totalAmount').val(salesAmount-subsidyAmount);
}

/**
 * @name      : 단말기 판매 onclick 이벤트 시 수행되는 함수
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-26
 */
function completeSale() {

    debugger;
    /* 데이터 세팅 */
    let subscriptionId = document.getElementById('customerIDInput').value;
    let deviceCode = document.getElementById('deviceModel').value;
    let deviceNumber = document.getElementById('serialNumber').value;
    let planCode = document.getElementById('plan').value;
    let subsidyAmount = "";
    let paymentAmount = "";

    const selectedDeviceInfo = allDeviceInfos.find(device => device.deviceCode === deviceCode);
    let devicePrice = "";
    if (selectedDeviceInfo) {
        devicePrice = selectedDeviceInfo.devicePrice;
        console.log("선택된 디바이스의 가격:", devicePrice);
    } else {
        console.error("해당 deviceCode에 대한 디바이스 정보를 찾을 수 없습니다.");
    }

    /* 유효성 체크 */
    if (!subscriptionId || !deviceCode || !deviceNumber || !planCode || isNaN(devicePrice) || isNaN(subsidyAmount) || isNaN(paymentAmount)) {
        alert('판매에 필요한 데이터를 모두 입력하세요.');
        return;
    }

    /* 판매 데이터 세팅 */
    const saleData = {
        subscription_id: subscriptionId,
        device_code: deviceCode,
        device_number: deviceNumber,
        plan_code: planCode,
        device_usage: devicePrice,
        support_amt: subsidyAmount,
        sale_amount: paymentAmount
    };

    /* 판매 처리 */
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
 * @name      : 단말 판매 처리 api
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-03-01
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
                alert('판매가 완료되었습니다.');
            } else {
                return response.text().then(errorMsg => {
                    throw new Error(`판매에 실패했습니다: ${errorMsg}`);
                });
            }
        })
        .catch(error => {
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



