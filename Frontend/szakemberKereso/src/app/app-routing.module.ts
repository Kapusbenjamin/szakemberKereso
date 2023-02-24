import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './_components/not-found/not-found.component';
import { AuthGuard } from './_Guards/auth.guard';

const routes: Routes = [
  {path: 'main',
  // canLoad: [AuthGuard],
  loadChildren: ()=> import('./_components/main/main.module')
  .then(m => m.MainModule)
  },
  {path:'login',
  loadChildren: ()=> import('./_components/login/login.module')
  .then(m => m.LoginModule)
  },
  {
    path: '**', component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
 }
