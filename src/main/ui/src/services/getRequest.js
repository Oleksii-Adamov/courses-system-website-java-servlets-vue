import {authorizationHeaders} from "@/authUtils";

export default async function getRequest(url) {
    const response = await fetch(url, {method: 'GET', headers: await authorizationHeaders()});
    if (response.status === 200) {
        return response.json();
    }
    else {
        response.text().then(text => { alert(text); console.error(text); });
        return undefined;
    }
}