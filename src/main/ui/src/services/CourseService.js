import getRequest from "@/services/getRequest";

export async function getStudentCourses() {
    return getRequest('/api/courses/student-courses');
}
export async function getTeacherCourses() {
    return getRequest('/api/courses/teacher-courses');
}

export async function getCourseStudents(courseId) {
    return getRequest('/api/courses/course-students?courseId='+courseId);
}