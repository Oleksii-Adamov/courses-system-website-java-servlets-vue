import LandingPage from "@/pages/LandingPage";
import ChooseRolePage from "@/pages/ChooseRolePage";
import StudentCoursesPage from "@/pages/StudentCoursesPage";

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
    }
]