const exampleModal = document.getElementById('exampleModal');
const tableBody = document.getElementById('tableBody');

if (exampleModal) {
    const modalLabel = document.getElementById('modalLabel');
    const updateForm = document.getElementById('updateForm');
    const inputNumber = document.getElementById("inputNumber");
    const updateButton = document.getElementById("updateButton");
    let objectId

    $('#exampleModal').on('show.bs.modal', (event) => {
        const button = event.relatedTarget;

        objectId = button.dataset.id;
        const objectName = button.dataset.name;
        const objectQuantity = button.dataset.quantity;

        modalLabel.textContent = `Thay đổi số lượng tồn kho của sản phẩm "${objectName}"`;
        inputNumber.value = objectQuantity
    });

    updateButton.onclick = function (e) {
        if (!objectId) {
            return
        }
        updateForm.action = `/kiemke?quantity=${inputNumber.value}&id=${objectId}`;
        updateForm.submit()
    }
}
(function ($) {
    "use strict"; // Start of use strict
    // Scroll to top button appear
    $(document).on('scroll', function () {
        var scrollDistance = $(this).scrollTop();
        if (scrollDistance > 100) {
            $('.scroll-to-top').fadeIn();
        } else {
            $('.scroll-to-top').fadeOut();
        }
    });

    // Smooth scrolling using jQuery easing
    $(document).on('click', 'a.scroll-to-top', function (e) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top)
        }, 200, 'easeInOutExpo');
        e.preventDefault();
    });

})(jQuery); // End of use strict

async function getData() {
    return fetch("/test", {
        method: 'GET',
        headers: {
            'Content-type': 'application/json',
        },
    }).then((res) => res.json())
}


function render(data, dataTable, selected, state) {
    dataTable.clear()
    data.forEach(item => {
        const found = selected.find(child => child.id == item.id)
        if (found) {
            return
        }
        const buttonText = state == 'normal'
            && `<button class="btn btn-primary mb-2" data-toggle="modal"
                            data-name='${item.displayName}'
                            data-id='${item.id}'
                            data-quantity='${item.quantity}'
                            data-target="#exampleModal">
                        sửa
                    </button>`
            || `<button class="btn btn-success kiemKeButton" 
                            data-name='${item.displayName}'
                            data-id='${item.id}'
                            data-input='${item.input_id}'
                            data-quantity='${item.quantity}'>
                        Thêm
                    </button>`
        const values = [
            item.id,
            item.displayName,
            item.quantity,
            item.inputPrice,

            item.status,
            item.input_id,
            item.date,
            buttonText
        ]
        dataTable.row.add(values).draw()
    })

}

function renderKiemKe(selected) {
    const noiDungKiemKe = document.getElementById('noiDungKiemKe')
    const kiemKeSubmit = document.getElementById('kiemKeSubmit')
    noiDungKiemKe.innerHTML = ''


    selected.forEach(child => {
        const text = `
            <th>${child.id}</th>
            <th>${child.input}</th>
            <th>${child.name}</th>
            <th>${child.quantity}</th>
            <th>
                <input type="number" min="0" 
                    data-id="${child.id}"
                    data-input="${child.input}" 
                    data-quantity='${child.quantity}' 
                    value=${child.quantity}
                    class="specialChild"
                    placeholder="Số lượng">
            </th>
            <th>
                <button class="btn btn-danger" data-id="${child.id}">
                    Xóa
                </button>
            </th>`
        noiDungKiemKe.innerHTML += text
    })
    kiemKeSubmit.style.visibility = selected.length > 0 && 'visible' || 'hidden'
}

if (tableBody) {
    const phieuKiemKe = document.getElementById('phieuKiemKe')
    const specialButton = document.getElementById('specialButton')
    const kiemKeSubmit = document.getElementById('kiemKeSubmit')
    const exampleName = document.getElementById('exampleName')

    const data = await getData();
    let selected = []
    let dataTable = $('#dataTable').DataTable();
    let state = 'normal';

    phieuKiemKe.style.display = 'none'
    render(data, dataTable, selected, state)
    renderKiemKe(selected)

    $('#noiDungKiemKe').on('click', 'button.btn-danger', function (e) {
        const objectId = e.target.dataset.id
        selected = selected.filter(child => child.id != objectId)

        render(data, dataTable, selected, state)
        renderKiemKe(selected)
    })
    $('#tableBody').on("click", "button.kiemKeButton", function (e) {
        const dataset = e.target.dataset
        selected.push({
            name: dataset.name,
            id: dataset.id,
            input: dataset.input,
            quantity: dataset.quantity,
        })
        render(data, dataTable, selected, state)
        renderKiemKe(selected)
    })

    specialButton.onclick = function () {
        if (state === 'normal') {
            const date = new Date()
            const dateString = `${date.toLocaleDateString()} ${date.toLocaleTimeString()}`
            state = 'active'

            exampleName.value = `Phiếu kiểm kê ngày ${dateString}`
            specialButton.classList = 'btn btn-danger'
            specialButton.innerHTML = 'Hủy tạo phiếu'
            phieuKiemKe.style.display = 'block'
        } else {
            state = 'normal'
            specialButton.classList = 'btn btn-success'
            specialButton.innerHTML = 'Tạo phiếu kiểm kê'
            phieuKiemKe.style.display = 'none'
        }
        render(data, dataTable, selected, state)
    }
    kiemKeSubmit.onclick = function (e) {
        const name = exampleName.value.trim();
        const list = document.querySelectorAll('.specialChild')
        if (selected.length < 1 || !name) {
            e.preventDefault()
            return
        }
        const values = []
        list.forEach(child => {
            const objectId = child.dataset.id
            const inputId = child.dataset.input
            const objectQuantity = child.dataset.quantity
            values.push({
                id: objectId,
                input: inputId,
                quantity: objectQuantity,
                newQuantity: child.value
            })
        })
        fetch("/test", {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify({name, values})
        }).then(() => location.reload())
    }
}