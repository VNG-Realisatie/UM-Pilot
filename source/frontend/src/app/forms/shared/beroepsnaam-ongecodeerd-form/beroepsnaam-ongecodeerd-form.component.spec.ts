import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BeroepsnaamOngecodeerdFormComponent} from './beroepsnaam-ongecodeerd-form.component';

describe('BeroepsnaamOngecodeerdFormComponent', () => {
  let component: BeroepsnaamOngecodeerdFormComponent;
  let fixture: ComponentFixture<BeroepsnaamOngecodeerdFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BeroepsnaamOngecodeerdFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeroepsnaamOngecodeerdFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
