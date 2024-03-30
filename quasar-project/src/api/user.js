import {instance } from "./config"

export const fetchUserApi = async() => {
  const response = await instance.get("/user/")
  return response;
}


export const setUserRoleApi = async() => {
  const response = await instance.post("/user/roles")
  return response;
}
