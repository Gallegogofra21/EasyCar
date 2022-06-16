import { Component, OnInit } from '@angular/core';
import { Concesionario } from 'src/app/models/concesionario-interface';
import { ConcesionarioService } from 'src/app/services/concesionario.service';

@Component({
  selector: 'app-concesionario-list',
  templateUrl: './concesionario-list.component.html',
  styleUrls: ['./concesionario-list.component.css']
})
export class ConcesionarioListComponent implements OnInit {
  concesionarioList!: Concesionario[];
  paginas!: number;

  constructor(private concesionarioService: ConcesionarioService) { }

  ngOnInit(): void {
    this.getConcesionarios();
  }

  getConcesionarios() {
    this.concesionarioService.getConcesionarios().subscribe(res => {
      this.concesionarioList = res.content;
      this.paginas = res.totalPages;
    })
  }

}
