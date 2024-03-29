import { route } from 'quasar/wrappers'
import { createRouter, createMemoryHistory, createWebHistory, createWebHashHistory } from 'vue-router'
import routes from './routes'
import { Notify } from 'quasar'
import { LocalStorage } from 'quasar'

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

export default route(function (/* { store, ssrContext } */) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : (process.env.VUE_ROUTER_MODE === 'history' ? createWebHistory : createWebHashHistory)

  const Router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,

    // Leave this as is and make changes in quasar.conf.js instead!
    // quasar.conf.js -> build -> vueRouterMode
    // quasar.conf.js -> build -> publicPath
    history: createHistory(process.env.VUE_ROUTER_BASE)
  })

  Router.beforeEach((to, from, next) => {
    let isAdmin = false
    const roles = LocalStorage.getItem('roles')
    console.log('@TODO: 根据Role判断路由权限', roles)
    isAdmin = Array.from(roles).some((item) => {
      console.log(item)
      return item.name == 'ROLE_ADMIN' 
    })
    if(isAdmin) console.log('you are a admin')
    if(to.name === 'user' && !isAdmin) {
      Notify.create({
        message: "you have no permission to userPage"
      })
      next(from)
    }else{
      next()
    }
  })
  return Router
})
