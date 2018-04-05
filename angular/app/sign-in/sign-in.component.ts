import {Component, Input, OnInit} from '@angular/core';
import {SignInService} from '../sign-in.service';
import {User} from '../entities/User';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  @Input() user: User = {
    id: null,
    login: '',
    password: ''
  };

  constructor(private signInService: SignInService) {
  }

  ngOnInit() {

  }

  signIn(login: string, password: string) {
    return this.signInService.signIn(login, password);
  }
}
