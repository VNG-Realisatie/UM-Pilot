import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';

import {Observable} from 'rxjs';
import {filter, map, tap} from 'rxjs/operators';
import {AuthService} from '../services/auth.service';

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {

  private isAuthenticated!: boolean;

  constructor(
    private authService: AuthService
  ) {
    this.authService.isAuthenticated$.subscribe(i => this.isAuthenticated = i);
  }

  ngOnInit() {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.authService.isDoneLoading$
      .pipe(filter(isDone => isDone))
      .pipe(tap(_ => this.isAuthenticated || this.authService.login(state.url)))
      .pipe(map(_ => this.isAuthenticated));
  }
}
