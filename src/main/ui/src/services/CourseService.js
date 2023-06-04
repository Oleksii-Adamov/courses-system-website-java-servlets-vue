export async function getStudentCourses() {
const response = await fetch('/api/student-courses');
    return await response.json();
}