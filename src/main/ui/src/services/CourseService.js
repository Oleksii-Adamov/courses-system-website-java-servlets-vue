export async function getStudentCourses() {
    const response = await fetch('/api/courses/student-courses');
    console.log(response.status);
    return response.json();
}