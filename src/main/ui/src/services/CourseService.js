import getRequest from "@/services/getRequest";

export async function getStudentCourses() {
    return getRequest('/api/courses/student-courses');
}
export async function getTeacherCourses() {
    return getRequest('/api/courses/teacher-courses');
}