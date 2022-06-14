import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConcesionarioListComponent } from '../components/concesionario-list/concesionario-list.component';
import { LoginComponent } from '../components/login/login.component';
import { MarcaListComponent } from '../components/marca-list/marca-list.component';
import { RegisterComponent } from '../components/register/register.component';
import { TipoListComponent } from '../components/tipo-list/tipo-list.component';
import { UserListComponent } from '../components/user-list/user-list.component';
import { VehiculoListComponent } from '../components/vehiculo-list/vehiculo-list.component';

const routes: Routes = [
  {path: 'login', pathMatch: 'full', component: LoginComponent},
  {path: 'tipos', component: TipoListComponent},
  {path: 'marcas', component: MarcaListComponent},
  {path: 'vehiculos', component: VehiculoListComponent},
  {path: 'users', component: UserListComponent},
  {path: 'concesionarios', component: ConcesionarioListComponent},
  {path: 'register', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
