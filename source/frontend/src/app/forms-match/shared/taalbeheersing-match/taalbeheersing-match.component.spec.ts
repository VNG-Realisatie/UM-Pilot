import {ComponentFixture, TestBed} from '@angular/core/testing';

import {TaalbeheersingMatchComponent} from './taalbeheersing-match.component';

describe('TaalbeheersingMatchComponent', () => {
  let component: TaalbeheersingMatchComponent;
  let fixture: ComponentFixture<TaalbeheersingMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TaalbeheersingMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TaalbeheersingMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
