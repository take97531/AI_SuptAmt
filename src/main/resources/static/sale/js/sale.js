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
    debugger;

    const phoneNumberInput = document.getElementById('tbx_custTelNumber');

    /* 전화번호 tbx 포맷팅 Event */
    phoneNumberInput.addEventListener('input', autoFormatPhoneNumber);

    /* 전화번호 tbx keyPrss Event */
    phoneNumberInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            e.preventDefault();
            validateAndCheckCustomer();
        }
    });

    /* 단말 번호 입력 입력 박스 keyPrss Event */
    const serialNumberInput = document.getElementById('serialNumber');
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
 * @name      : 단말 전체 정보 조회 api
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-28
 */
async function fetchDeviceInfos() {
    debugger;
    try {
        const deviceInfos = await $.ajax({
            url: '/api/v1/device/deviceinfo/alldeviceinfo',
            type: 'GET',
            success: function(deviceInfos) {
                debugger;
                console.log("단말기 전체 정보 :", deviceInfos);
                const deviceModelSelect = document.getElementById('deviceModel');
                deviceModelSelect.innerHTML = '<option value="">단말기 모델 선택</option>';

                /* 조회된 단말기 정보를 selectBox에 세팅 */
                deviceInfos.forEach(function(deviceInfo) {
                    const option = new Option(deviceInfo.deviceName, deviceInfo.deviceCode);
                    deviceModelSelect.add(option);
                });

            },
            error: function(error) {
                console.error("단말기 정보 조회 실패:", error);
                alert('단말기 정보를 조회할 수 없습니다.');
            }
        });
    } catch (error) {
        console.error("단말기 정보 조회 실패:", error);
        alert('단말기 정보를 조회할 수 없습니다.');
    }
}

/**
 * @name      : 단말기 정보 조회 api
 * @작성자     : 권유리
 * @수정자     :
 * @작성일자   : 2024-02-26
 */
function fetchDeviceInventory() {

    let deviceCode = document.getElementById('deviceModel').value;
    let deviceNumber = document.getElementById('serialNumber').value;
    deviceNumber = deviceNumber.padStart(20, '0');

    /* 단말기 유효성 체크 */
    if (!deviceCode || !deviceNumber) {
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


function selectPlan() {
    // 요금제 선택 로직
    alert('요금제 선택 완료');
    // 다음 섹션 보이기
    document.getElementById('selectPayment').style.display = 'block';
}

function selectSubsidy() {
    // 여기에 지원금 선택 처리 로직을 구현합니다.
    alert("지원금 선택이 완료되었습니다.");
}

function completeSale() {
    // 판매 완료 로직
    alert('판매가 완료되었습니다.');
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



