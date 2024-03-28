import { instance } from "./config";


export const loginApi = async({email, password}) => {
    const response = await instance.post("/auth/login", {
        email: email,
        password: password
    });
    return response;
}

export const registerApi  = async({firstName, lastName, email, password}) => {
    const response = await instance.post("/auth/register", {
        firstName: firstName , 
        lastName: lastName , 
        email: email, 
        password: password 
    });
    return response;
}