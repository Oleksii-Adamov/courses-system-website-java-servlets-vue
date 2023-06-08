//import Vue from 'vue'
import VueRouter from 'vue-router'
import LandingPage from "@/pages/LandingPage";
import ChooseRolePage from "@/pages/ChooseRolePage";
import StudentCoursesPage from "@/pages/StudentCoursesPage";
//Vue.use(VueRouter)
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
// const router = new VueRouter({
//     mode: 'history',
//     linkExactActiveClass: 'active',
//     linkActiveClass: 'active',
//     routes: [
//         {
//             path: '/',
//             component: LandingPage
//         },
//         {
//             path: '/choose-role',
//             component: ChooseRolePage
//         },
//         {
//             path: '/student-courses',
//             component: StudentCoursesPage
//         }
//     ]
// })
// console.log(router)
// export default router