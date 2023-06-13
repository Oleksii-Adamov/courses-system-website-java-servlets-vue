import {authorizationHeaders} from "@/authUtils";

export async function getStudentCourses() {
    const response = await fetch('/api/courses/student-courses', {method: 'GET', headers: await authorizationHeaders()});
    console.log(response.status);
    // response.text().then(text => { console.log(text) });
    return response.json();
}
export async function getTeacherCourses() {
    const response = await fetch('/api/courses/teacher-courses', {method: 'GET', headers: await authorizationHeaders()});
    console.log(response.status);
    return response.json();
}