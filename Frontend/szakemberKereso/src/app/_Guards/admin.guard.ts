import { Injectable } from '@angular/core';
import { CanLoad, Route, Router, UrlSegment, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UsersService } from '../_services/users.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanLoad {

  constructor(private userService: UsersService, private router: Router) {}

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    const userData = this.userService.userData;
    if (userData.userId > 0 && userData.access_type === 2) {
      return true;
    } else {
      this.router.navigate(['/main']);
      return false;
    }
  }

}
