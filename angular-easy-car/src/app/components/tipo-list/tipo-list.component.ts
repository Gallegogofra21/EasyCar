import { Component, OnInit } from '@angular/core';
import { Tipo } from 'src/app/models/tipo-interface';
import { TipoService } from 'src/app/services/tipo.service';

@Component({
  selector: 'app-tipo-list',
  templateUrl: './tipo-list.component.html',
  styleUrls: ['./tipo-list.component.css']
})
export class TipoListComponent implements OnInit {
  tipoList!: Tipo[];
  paginas!: number;

  constructor(private tipoService: TipoService) { }

  ngOnInit(): void {
    this.getTipos();
  }

  getTipos() {
    this.tipoService.getTipos().subscribe(res => {
      this.tipoList = res.content;
      this.paginas=res.totalPages;
    })
  }

}
