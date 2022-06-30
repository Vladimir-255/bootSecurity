function deleteUserModal(id) {
    fetch("http://localhost:8080/admin/api/users/" + id)
        .then(jData => jData.json())
        .then(data => {
            window.id1.value = data.id
            window.name1.value = data.name
            window.lastname1.value = data.lastname
            window.age1.value = data.age
            window.email1.value = data.email
            window.username1.value = data.username
            window.roleId1Del.checked = false
            window.roleId0Del.checked = false
            data.roles.forEach(role => {
                if (role.role === "ADMIN") {
                    window.roleId0Del.checked = true
                }
                if (role.role === "USER") {
                    window.roleId1Del.checked = true
                }
            })

        })
}