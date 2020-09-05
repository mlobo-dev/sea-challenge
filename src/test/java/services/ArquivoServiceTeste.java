package services;

import com.wolftech.sea.dto.ArquivoDTO;
import com.wolftech.sea.entity.Arquivo;
import com.wolftech.sea.exception.DataIntegrityException;
import com.wolftech.sea.exception.ObjectNotFoundException;
import com.wolftech.sea.mapper.ArquivoMapper;
import com.wolftech.sea.repositories.ArquivoRepository;
import com.wolftech.sea.services.ArquivoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class ArquivoServiceTeste {

    @InjectMocks
    ArquivoService service;

    @Mock
    ArquivoRepository repository;

    @Mock
    ArquivoMapper mapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSave() throws Exception {
        when(repository.save(any(Arquivo.class))).thenReturn(new Arquivo());
        when(mapper.toEntity(any(ArquivoDTO.class))).thenReturn(new Arquivo());
        Arquivo result = service.salvar(criarArquivoDTO());
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldEdit() {
        Arquivo cargoAlteracao = criarArquivo();
        cargoAlteracao.setBase64(new byte[99]);
        when(repository.save(any(Arquivo.class))).thenReturn(cargoAlteracao);
        when(repository.findById(anyLong())).thenReturn(Optional.of(criarArquivo()));
        when(mapper.toEntity(any(ArquivoDTO.class))).thenReturn(new Arquivo());
        ArquivoDTO cargoDTO = criarArquivoDTO();
        cargoDTO.setBase64(new byte[232]);
        Arquivo result = service.editar(cargoDTO);
        Assert.assertTrue(result != null);
    }


    @Test
    public void shouldReturnArquivoById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Arquivo()));
        Arquivo result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionWhenArquivoDontExistsById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Arquivo result = service.buscarPorId(1L);
        Assert.assertTrue(result != null);
    }

    @Test
    public void shouldReturnAnListOfArquivo() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(new Arquivo()));
        List<Arquivo> result = service.listarTudo();
        Assert.assertTrue(result != null);
        Assert.assertTrue(result.size() > 0);

    }

    @Test
    public void shouldDeleteArquivo() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Arquivo()));
        service.deletarArquivo(1L);
    }

    @Test(expected = DataIntegrityException.class)
    public void shouldThrowExcpetiononDeleteArquivo() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(new Arquivo()));
        doThrow(DataIntegrityException.class).when(repository).deleteById(anyLong());
        service.deletarArquivo(1L);
    }


    private ArquivoDTO criarArquivoDTO() {
        ArquivoDTO obj = new ArquivoDTO();
        obj.setBase64(new byte[323]);
        obj.setId(1L);
        return obj;
    }

    private Arquivo criarArquivo() {
        Arquivo obj = new Arquivo();
        obj.setBase64(new byte[323]);
        obj.setId(1L);
        return obj;
    }
}
