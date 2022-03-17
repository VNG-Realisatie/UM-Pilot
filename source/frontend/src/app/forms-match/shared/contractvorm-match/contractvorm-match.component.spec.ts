import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ContractvormMatchComponent} from './contractvorm-match.component';

describe('ContractvormMatchComponent', () => {
  let component: ContractvormMatchComponent;
  let fixture: ComponentFixture<ContractvormMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ContractvormMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContractvormMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
