import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:'',
  loadChildren: ()=> import('./_components/login-page/login-page.module')
  .then(m => m.LoginPageModule)
  },
  {path: 'main',
  loadChildren: ()=> import('./_components/main-page/main-page.module')
  .then(m => m.MainPageModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
