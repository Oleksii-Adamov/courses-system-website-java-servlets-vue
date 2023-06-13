import LandingPage from "@/pages/LandingPage";
import ChooseRolePage from "@/pages/ChooseRolePage";
import StudentCoursesPage from "@/pages/StudentCoursesPage";
import TeacherCoursesPage from "@/pages/TeacherCoursesPage";
import CreateCoursePage from "@/pages/CreateCoursePage";
import JoinCoursePage from "@/pages/JoinCoursePage";

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
        path: '/create-course',
        component: CreateCoursePage
    },
    {
        path: '/join-course',
        component: JoinCoursePage
    }
]