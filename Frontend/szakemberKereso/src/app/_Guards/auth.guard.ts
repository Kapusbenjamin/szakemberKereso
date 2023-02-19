import { Injectable } from '@angular/core';
import { CanLoad, Route, UrlSegment, Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UsersService } from '../_services/users.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanLoad {
  constructor(private userService: UsersService, private router: Router) {}

  canLoad(
    route: Route,
    segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
      if (this.userService.userData.userId && this.userService.userData.userId > 0) {
        console.log(this.userService.userData.userId);
        // user is logged in and has a valid userId
        return true;
      } else {
        console.log(this.userService.userData.userId);
        // user is not logged in, navigate to login page
        this.router.navigate(['/login']);
        return false;
      }
  }

}
