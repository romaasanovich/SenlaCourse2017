import {Component, Input, OnInit} from '@angular/core';
import {Master} from '../entities/Master';

@Component({
  selector: 'app-master-detail',
  templateUrl: './master-detail.component.html',
  styleUrls: ['./master-detail.component.css']
})
export class MasterDetailComponent implements OnInit {
  @Input() master: Master;

  constructor() { }

  ngOnInit() {
  }

}
