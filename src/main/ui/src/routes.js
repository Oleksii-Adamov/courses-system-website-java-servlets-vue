import LandingPage from "@/pages/LandingPage";
import ChooseRolePage from "@/pages/ChooseRolePage";
import StudentCoursesPage from "@/pages/StudentCoursesPage";
import TeacherCoursesPage from "@/pages/TeacherCoursesPage";
import CreateCoursePage from "@/pages/CreateCoursePage";
import JoinCoursePage from "@/pages/JoinCoursePage";
import TeacherCoursePage from "@/pages/TeacherCoursePage";
import GradeStudentPage from "@/pages/GradeStudentPage";
import StudentCourseGradePage from "@/pages/StudentCourseGradePage";

export default [
    {
        path: '/',
        component: LandingPage
    },
    {
        path: '/choose-role',
        component: ChooseRolePage
    },
    {
        path: '/student-courses',
        component: StudentCoursesPage
    },
    {
        path: '/teacher-courses',
        component: TeacherCoursesPage
    },
    {
        path: '/teacher-courses/:id',
        name: 'teacher-course',
        component: TeacherCoursePage,
        props: true,
    },
    {
        path: '/create-course',
        component: CreateCoursePage
    },
    {
        path: '/join-course',
        component: JoinCoursePage
    },
    {
        path: '/grade-student',
        name: 'grade-student',
        component: GradeStudentPage,
        props: true,
    },
    {
        path: '/student-courses/:id',
        name: 'student-course',
        component: StudentCourseGradePage,
        props: true,
    }
]