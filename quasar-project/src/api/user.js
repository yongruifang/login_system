import {instance } from "./config"

export const fetchUserApi = async() => {
  const response = await instance.get("/user/")
  return response;
}


export const setUserRoleApi = async({
  email,
  is_admin,
  is_user
}) => {
  let roles = []
  if(is_admin) {
    roles.push({
      "id" : "1",
      "name" : "ROLE_ADMIN"
    })
  }
  if(is_user) {
    roles.push ({
      "id": "2",
      "name": "ROLE_USER"
    })
  }
  const response = await instance.post("/user/roles", {
    email: email,
    roles: roles
  })
  return response;
}
