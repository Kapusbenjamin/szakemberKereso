import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './_components/not-found/not-found.component';

const routes: Routes = [
  {path:'',
  loadChildren: ()=> import('./_components/login/login.module')
  .then(m => m.LoginModule)
  },
  {path: 'main',
  loadChildren: ()=> import('./_components/main/main.module')
  .then(m => m.MainModule)
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
