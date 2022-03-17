import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CursusMatchComponent} from './cursus-match.component';

describe('CursusMatchComponent', () => {
  let component: CursusMatchComponent;
  let fixture: ComponentFixture<CursusMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CursusMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CursusMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
