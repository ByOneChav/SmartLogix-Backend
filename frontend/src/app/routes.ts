import { Routes } from '@angular/router';
import { Login } from './features/auth/pages/login/login';
import {MainLayout} from "./layouts/main-layout/main-layout";
import {authGuard} from "./core/guards/auth-guard";

export const routes: Routes = [

  {
    path: 'login',
    component: Login
  },

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {path: 'dashboard',component: MainLayout,canActivate: [authGuard] ,children: [
      {path: '', loadComponent: ()=> import('./features/dashboard/pages/dashboard/dashboard').then(m => m.Dashboard)},
      {path: 'inventario', loadComponent: () => import('./features/inventario/pages/inventario/inventario').then(m => m.Inventario)},
    ]
  },
  {path: '**', redirectTo: 'login'}

];
