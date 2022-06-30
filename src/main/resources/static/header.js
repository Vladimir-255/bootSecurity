fetch("http://localhost:8080/admin/api/user/")
    .then(data => data.json())
    .then(jdata => {
        window.spanUserUsername.textContent = jdata.username
        window.spanUserRoles.textContent = jdata.rolesToString
        window.textUserId.textContent = jdata.id
        window.textUserName.textContent = jdata.name
        window.textUserLastName.textContent = jdata.lastname
        window.textUserAge.textContent = jdata.age
        window.textUserEmail.textContent = jdata.email
        window.textUserUsername.textContent = jdata.username
        window.textUserRoles.textContent = jdata.rolesToString

    })