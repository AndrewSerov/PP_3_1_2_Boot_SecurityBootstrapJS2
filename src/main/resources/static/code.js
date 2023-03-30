const url = 'http://localhost:8080/admin/get/';
const container = document.querySelector('tbody');
let result = '';

const userModal = new bootstrap.Modal(document.getElementById('userModal'));
const userForm = document.querySelector('form');
const username = document.getElementById('username');
const lastname = document.getElementById('lastname');
const password = document.getElementById('password');

let option = '';

add.addEventListener("click", () => {
    username.value = ''
    lastname.value = ''
    password.value = ''

    userModal.show();
    option = 'create'

})
const show = (user) => {

    user.forEach(user => {
        result += `
    <tr>
    <td>${user.id}</td>
    <td>${user.username}</td>
    <td>${user.lastname}</td>
    <td>${user.password}</td>
    <td class = "text-center"><a class = "btnEdit btn btn-primary">Edit</a><a class = "btnDelete btn btn-danger">Delete</a></td>
    
    </tr>
    `
    });
    container.innerHTML = result;
}
fetch(url)
    .then(response => response.json())
    .then(data => show(data))


const on = (element, event, selector, handler) => {

    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

on(document, "click", '.btnDelete', e => {
    const row = e.target.parentNode.parentNode
    const id = row.firstElementChild.innerHTML
    console.log(id)
    fetch(url + id, {
        method: 'DELETE'
    })
        .then(res => res.json())
        .then(() => location.reload())

})

let idForm = 0
on(document, "click", '.btnEdit', e => {
    const row = e.target.parentNode.parentNode
    idForm = row.children[0].innerHTML
    const usernameForm = row.children[1].innerHTML
    const lastnameForm = row.children[2].innerHTML
    const passwordForm = row.children[3].innerHTML
    username.value = usernameForm
    lastname.value = lastnameForm
    password.value = passwordForm
    option = 'edit'
    userModal.show()

})

userForm.addEventListener('submit', (e) => {
        e.preventDefault()
        if(option == 'create') {
            fetch(url, {
                method:'POST',
                headers: {
                    'Content-type':'application/json'
                },
                body:JSON.stringify({
                    username:username.value,
                    lastname:lastname.value,
                    password:password.value
                })
            })
                .then(response => response.json())
                .then(data => {
                    const newUser = []
                    newUser.push(data)
                    show(newUser)
                })
        }
        if(option == 'edit') {
            fetch(url, {
                method:'PUT',
                headers: {
                    'Content-type':'application/json'
                },
                body:JSON.stringify({
                    id:idForm,
                    username:username.value,
                    lastname:lastname.value,
                    password:password.value
                })
            })
                .then(response => response.json())
                .then(response => location.reload())
        }
        userModal.hide()
    }
)