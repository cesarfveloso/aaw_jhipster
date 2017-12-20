import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Aula3PermissaoModule } from './permissao/permissao.module';
import { Aula3UsuarioModule } from './usuario/usuario.module';
import { Aula3AlunoModule } from './aluno/aluno.module';
import { Aula3FaltaModule } from './falta/falta.module';
import { Aula3NotaModule } from './nota/nota.module';
import { Aula3AulaModule } from './aula/aula.module';
import { Aula3ProfessorModule } from './professor/professor.module';
import { Aula3TurmaModule } from './turma/turma.module';
import { Aula3EntregaModule } from './entrega/entrega.module';
import { Aula3DisciplinaModule } from './disciplina/disciplina.module';
import { Aula3AtividadeModule } from './atividade/atividade.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        Aula3PermissaoModule,
        Aula3UsuarioModule,
        Aula3AlunoModule,
        Aula3FaltaModule,
        Aula3NotaModule,
        Aula3AulaModule,
        Aula3ProfessorModule,
        Aula3TurmaModule,
        Aula3EntregaModule,
        Aula3DisciplinaModule,
        Aula3AtividadeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Aula3EntityModule {}
