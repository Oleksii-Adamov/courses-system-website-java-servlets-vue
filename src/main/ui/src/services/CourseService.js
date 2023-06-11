export async function getStudentCourses() {
    const response = await fetch('/api/courses/student-courses');
    console.log(response.status);
    return response.json();
}
export async function getTeacherCourses() {
    const response = await fetch('/api/courses/teacher-courses');
    console.log(response.status);
    return response.json();
}