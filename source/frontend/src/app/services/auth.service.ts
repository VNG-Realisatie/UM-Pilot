import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {AuthConfig, OAuthService} from 'angular-oauth2-oidc';
import {BehaviorSubject, ReplaySubject} from 'rxjs';

@Injectable({providedIn: 'root'})
export class AuthService {

  private isAuthenticatedSubject$ = new BehaviorSubject<boolean>(false);
  public isAuthenticated$ = this.isAuthenticatedSubject$.asObservable();

  private isDoneLoadingSubject$ = new ReplaySubject<boolean>();
  public isDoneLoading$ = this.isDoneLoadingSubject$.asObservable();

  private oin?: string;

  authConfig: AuthConfig = {
    issuer: 'http://localhost:8080/auth/realms/poc-vng-realm',
    redirectUri: window.location.origin,
    clientId: 'poc-vng-frontend',
    scope: 'openid profile email offline_access',
    responseType: 'code',
    // at_hash is not present in JWT token
    disableAtHashCheck: true,
    showDebugInformation: true
  }

  constructor(
    private oauthService: OAuthService,
    private router: Router,
  ) {
    this.configure();
  }

  public login(targetUrl?: string) {
    this.oauthService.initLoginFlow(targetUrl || this.router.url);
  }

  public logout() {
    this.oauthService.logOut();
  }

  public refresh() {
    this.oauthService.silentRefresh();
  }

  public hasValidToken() {
    return this.oauthService.hasValidAccessToken();
  }

  private configure() {
    this.oauthService.configure(this.authConfig);
    this.oauthService.setupAutomaticSilentRefresh();
    this.oauthService.events.subscribe(_ => {
      this.isAuthenticatedSubject$.next(this.oauthService.hasValidAccessToken());
      const profile = this.oauthService.getIdentityClaims();
      if (profile) {
        //obtain oin after having received valid token
        this.oin = (profile as any)['oin'];
      }
    });
    this.oauthService.loadDiscoveryDocumentAndLogin()
      .then(() => this.isDoneLoadingSubject$.next(true))
      .catch(() => this.isDoneLoadingSubject$.next(true));
  }

  public getOin(): string {
    if (this.oin) {
      return this.oin;
    } else {
      console.log("Oin not assigned")
      return "undefined";
    }
  }


}
