function putUser() {
    const form = new FormData(document.getElementById("formEditUser"))
    let userData = {role: []}
    form.forEach((value, key) => {
        if(value==="ADMIN"){
            userData.role.push(value)
        }else if(value==="USER"){
            userData.role.push(value)
        }else {
            userData[key]=value}
    })
    console.log(userData)

    fetch("http://localhost:8080/admin/api/users/", {
        method: 'PUT',
        body: JSON.stringify(userData),
        headers: {'Content-type': 'application/json; charset=UTF-8'}
    })
        .then(response => adminPaneler())

}
