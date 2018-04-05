import {Component, OnInit} from '@angular/core';
import {Master} from '../entities/Master';
import {MasterService} from '../master.service';

@Component({
  selector: 'app-master',
  templateUrl: './master.component.html',
  styleUrls: ['./master.component.css']
})
export class MasterComponent implements OnInit {
  selectedMaster: Master;
  masters: Master[];


  constructor(private masterService: MasterService) {
  }

  ngOnInit() {
    this.getMasters();
  }

  getMasters(): void {
    this.masterService.getMasters().subscribe(masters => this.masters = masters);
  }

  addMaster(name: string) {
    this.masterService.addMaster(name);
  }

  onSelect(master: Master) {
    this.selectedMaster = master;
  }
}
