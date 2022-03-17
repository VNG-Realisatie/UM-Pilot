import {ComponentFixture, TestBed} from '@angular/core/testing';

import {TaalbeheersingFormComponent} from './taalbeheersing-form.component';

describe('TaalbeheersingFormComponent', () => {
  let component: TaalbeheersingFormComponent;
  let fixture: ComponentFixture<TaalbeheersingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TaalbeheersingFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaalbeheersingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
