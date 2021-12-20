import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NewAccount } from 'src/app/models/NewAccount';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-nueva-cuenta',
  templateUrl: './nueva-cuenta.component.html',
  styleUrls: ['./nueva-cuenta.component.css']
})
export class NuevaCuentaComponent implements OnInit {
  newAccount: NewAccount;
  username: string;
  password: string;
  errMsj: string;
  isLogged = false;
  createFail = false;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
  
  }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  onRegister(): void {
    this.newAccount = new NewAccount(this.username, this.password);
    this.authService.create(this.newAccount).subscribe(
      data => {
        this.toastr.success('Cuenta Creada', 'CREATED', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });

        this.router.navigate(['/']);
      },
      err => {
        console.log(err);
        this.errMsj = "No se pudo crear la cuenta";
        this.createFail = true;
      }
    );
  }

}
