export async function getAuthUser() {
    let response = await fetch("/users/current")
    return response.ok
        ? response.json()
        : null
}

export async function getAuthUsername() {
    return await getAuthUser()
        .then(authUser => authUser.username)
        .then(username => {
            return username === undefined ? null : username
        })
}

export async function updateUserInfo() {
    let authUser = await getAuthUser()
    if (authUser != null) {
        $("#usernameNavbar").text(authUser.username)
        $("#ID").text(authUser.id)
        $("#username").text(authUser.username)
        // -
        $("#name").text(authUser.name)
        $("#surname").text(authUser.surname)
        // -
        $("#age").text(authUser.age)
        let rolesText = " with roles: "
        for (let role of authUser.roles) {
            rolesText += `${role.role.substring(5)}   `
        }
        $("#userRolesNavbar").text(rolesText.substring(0, rolesText.length - 3))
        $("#roles").text(rolesText.substring(12, rolesText.length - 3))
    }
}
