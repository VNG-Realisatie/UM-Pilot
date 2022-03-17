import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  oin?: string;

  subscription?: Subscription

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
    this.subscription = this.authService.isDoneLoading$.subscribe(x => this.oin = this.authService.getOin());


  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }

}
