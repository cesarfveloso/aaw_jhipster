import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AawAula3PermissaoModule } from './permissao/permissao.module';
import { AawAula3UsuarioModule } from './usuario/usuario.module';
import { AawAula3AlunoModule } from './aluno/aluno.module';
import { AawAula3FaltaModule } from './falta/falta.module';
import { AawAula3NotaModule } from './nota/nota.module';
import { AawAula3AulaModule } from './aula/aula.module';
import { AawAula3ProfessorModule } from './professor/professor.module';
import { AawAula3TurmaModule } from './turma/turma.module';
import { AawAula3EntregaModule } from './entrega/entrega.module';
import { AawAula3DisciplinaModule } from './disciplina/disciplina.module';
import { AawAula3AtividadeModule } from './atividade/atividade.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AawAula3PermissaoModule,
        AawAula3UsuarioModule,
        AawAula3AlunoModule,
        AawAula3FaltaModule,
        AawAula3NotaModule,
        AawAula3AulaModule,
        AawAula3ProfessorModule,
        AawAula3TurmaModule,
        AawAula3EntregaModule,
        AawAula3DisciplinaModule,
        AawAula3AtividadeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AawAula3EntityModule {}
