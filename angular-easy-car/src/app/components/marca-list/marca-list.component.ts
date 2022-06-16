import { Component, OnInit } from '@angular/core';
import { Marca } from 'src/app/models/marca-interface';
import { MarcaService } from 'src/app/services/marca.service';

@Component({
  selector: 'app-marca-list',
  templateUrl: './marca-list.component.html',
  styleUrls: ['./marca-list.component.css']
})
export class MarcaListComponent implements OnInit {
  marcaList!: Marca[];
  paginas!: number;

  constructor(private marcaService: MarcaService) { }

  ngOnInit(): void {
    this.getMarcas();
  }

  getMarcas() {
    this.marcaService.getMarcas().subscribe(res => {
      this.marcaList = res.content;
      this.paginas = res.totalPages;
    })
  }

}
