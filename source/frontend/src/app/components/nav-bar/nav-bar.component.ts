import {Component, OnInit} from '@angular/core';
import {AuthService} from 'src/app/services/auth.service';
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(
    private authService: AuthService
  ) {
  }

  ngOnInit(): void {
  }

  logout() {
    this.authService.logout();
  }

}
