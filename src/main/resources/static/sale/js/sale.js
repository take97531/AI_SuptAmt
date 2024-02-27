document.getElementById('refreshPage').addEventListener('click', function() {
    window.location.reload();
});

/* ■■■■■>> [eventListener] onLoad */
document.addEventListener('DOMContentLoaded', function() {

    /* 전화번호 초기화 */
    const phoneNumberInput = document.getElementById('tbx_custTelNumber');
    phoneNumberInput.value = '010-';

    /* 전화번호 input textBox 포맷팅 */
    formatPhoneNumberInput();

    /* 단말모델 selectBox 초기화*/
    initializeModelSelection();
});

/* ■■■■■>> [function] 전화번호 input textBox 포맷팅 */
function formatPhoneNumberInput() {
    const phoneNumberInput = document.getElementById('tbx_custTelNumber');
    /* 숫자만 입력하고 자동 포맷팅 */
    phoneNumberInput.addEventListener('input', function(e) {
        let value = e.target.value.replace(/[^\d]/g, '');
        if (!value.startsWith('010')) {
            value = '010' + value;
        }
        if (value.length > 3) {
            value = value.slice(0, 3) + '-' + value.slice(3);
        }
        if (value.length > 8) {
            value = value.slice(0, 8) + '-' + value.slice(8, 12);
        }
        e.target.value = value;
    });

    /* 엔터키 입력 감지 */
    phoneNumberInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            e.preventDefault();
            checkCustomer();
        }
    });
}

/* ■■■■■>> [function] 단말 모델 selectBox 초기화 */
function initializeModelSelection() {
    const filterInput = document.getElementById('filterInput');
    const modelSelect = document.getElementById('sbx_trmMdlCd');
    const modelGrid = document.getElementById('modelGrid');

    // 예시 데이터
    const dataCollection = [
        { trmMdlCd: "model1", trmNum: "001", trmMdlNm: "Model 1", stock: 10 },
        { trmMdlCd: "model2", trmNum: "002", trmMdlNm: "Model 2", stock: 15 },
        // 추가 데이터
    ];

    // 초기 모델 그리드 업데이트
    updateModelGrid(dataCollection);
}

/* ■■■■■>> [eventListener] 단말 모델 selectBox keyup */
filterInput.addEventListener('keyup', function() {
    const searchValue = filterInput.value.toLowerCase();
    modelSelect.innerHTML = '';

    const filteredModels = dataCollection.filter(model => model.trmMdlNm.toLowerCase().includes(searchValue));
    filteredModels.forEach(model => {
        const option = new Option(model.trmMdlNm, model.trmMdlCd);
        modelSelect.add(option);
    });

    updateModelGrid(filteredModels);
});

function updateModelGrid(models) {
    const modelGrid = document.getElementById('modelGrid');
    modelGrid.innerHTML = '';
    models.forEach(model => {
        const modelInfo = document.createElement('div');
        modelInfo.textContent = `모델 코드: ${model.trmMdlCd}, 모델 번호: ${model.trmNum}, 모델명: ${model.trmMdlNm}, 재고: ${model.stock}`;
        modelGrid.appendChild(modelInfo);
    });
}

function checkCustomer() {
    const phoneNumber = document.getElementById('tbx_custTelNumber').value.replace(/-/g, '');
    if (phoneNumber.length < 11) {
        alert('전화번호는 총 11자리입니다.');
    } else {
        $.ajax({
            url: '/api/v1/sales/checkCustomer', // Controller의 경로와 일치해야 합니다. 예시에서는 ID가 1인 금액 정보를 조회합니다.
            type: 'get',
            data: {
              phone : phoneNumber
            },
            success: function(data) {
                console.log("data : " + data);
                if(data) {
                    alert('고객 확인 완료');
                    // 다음 섹션 보이기
                    document.getElementById('selectDevice').style.display = 'block';
                }
                else
                    alert('존재하지 않는 고객입니다.');
            },
            error: function(error) {
                console.log("error : " + error);
            }
        });

    }
}

function selectDevice() {
    // 단말 선택 로직
    alert('단말 선택 완료');
    // 다음 섹션 보이기
    document.getElementById('selectPlan').style.display = 'block';
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


