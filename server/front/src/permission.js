import router from "./router";
import Element from "element-ui";
import store from './store'

// 路由判断登录 根据路由配置文件的参数
router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requireAuth)) { 
        const userInfo = store.state.userInfo;
        if (store.state.username != '') { 
            if (to.path === '/login') {
                next({ path: '/' });
            } else {
                next();
            }
        } else {
            Element.Message({
                message: "asd！",
                type: 'error',
                duration: 2 * 1000
            });
            next({
                path: '/login'
            });
        }
    } else {
        next();
    }
});