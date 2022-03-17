import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BeroepsnaamGecodeerdFormComponent} from './beroepsnaam-gecodeerd-form.component';

describe('BeroepsnaamGecodeerdFormComponent', () => {
  let component: BeroepsnaamGecodeerdFormComponent;
  let fixture: ComponentFixture<BeroepsnaamGecodeerdFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BeroepsnaamGecodeerdFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeroepsnaamGecodeerdFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
