import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AdsAdminPageComponent } from './ads-admin-page/ads-admin-page.component';
import { UsersAdminPageComponent } from './users-admin-page/users-admin-page.component';
import { RatingsPageComponent } from './ratings-page/ratings-page.component';

const routes: Routes = [
   {
    path: '', component: AdminPageComponent,
     children: [
       { path:'users', component:UsersAdminPageComponent },
       { path:'ads', component:AdsAdminPageComponent },
       { path:'ratings', component:RatingsPageComponent },
     ]
   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
