function editUserModal(id) {
    fetch("http://localhost:8080/admin/api/users/" + id)
        .then(jData => jData.json())
        .then(data => {
            window.id0.value = data.id
            window.name0.value = data.name
            window.lastname0.value = data.lastName
            window.age0.value = data.age
            window.email0.value = data.email
            window.username0.value = data.username
            window.password0.value = data.password
            window.roleId0.checked = false
            window.roleId1.checked = false
            data.roles.forEach(role => {
                if (role.role === "ADMIN") {
                    window.roleId0.checked = true
                }
                if (role.role === "USER") {
                    window.roleId1.checked = true
                }
            })

        })
}

