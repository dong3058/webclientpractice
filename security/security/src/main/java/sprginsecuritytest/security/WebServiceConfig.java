package sprginsecuritytest.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
//@ComponentScan
public class WebServiceConfig {


    private final MemberService memberService;
    private final SucessHandler sucessHandler;
    private final FailHandler failHandler;
    //private final JwtAuthFilter jwtAuthFilter;
    private final MyProvider provider;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                //.requestMatchers("/","/errorauth","/login")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http/*.formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler(sucessHandler)
                        .failureHandler(failHandler))*/

                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests)->
                        authorizeRequests

                                .requestMatchers("/api1").hasRole("user")
                                .requestMatchers("/api2").hasRole("admin")
                                .requestMatchers("/user").permitAll()//hasRole("user")
                                .requestMatchers("/members/add","/loginfail","/errorauth","/","/favicon.ico","/login","/jwt/add","/jwt/login").permitAll()

                        )
                .exceptionHandling((exceptionConfig)->exceptionConfig.accessDeniedHandler(customDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint()))
                .addFilterBefore(jsonFilter(), UsernamePasswordAuthenticationFilter.class)
                //.authenticationManager(authenticationManagerjwt())
                //.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
        //ExceptionTranslationFilter 음 나중에 알아보도록 하자
                    /*.formLogin(form->form
                            .defaultSuccessUrl("/")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .permitAll())*/

                ; // 커스텀 로그인 필터 등록


        return http.build();
    }




    @Bean
    public CustomDeniedHandler customDeniedHandler(){

        return new CustomDeniedHandler();
    }
    @Bean
    public JsonFilter jsonFilter(){

        JsonFilter jsonLoginProcessFilter = new JsonFilter(new ObjectMapper());
        /*jsonLoginProcessFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.getWriter().println("Success Login");
        });*/
       jsonLoginProcessFilter.setAuthenticationManager(authenticationManagercustom());
       jsonLoginProcessFilter.setAuthenticationSuccessHandler(sucessHandler);
       jsonLoginProcessFilter.setAuthenticationFailureHandler(failHandler);

       //음... 이거못넣어서 그동안 개지랄을햇는대 뭔가 현타온다 씨2발....
       SecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();
        jsonLoginProcessFilter.setSecurityContextRepository(contextRepository);
        return jsonLoginProcessFilter;
    }



   @Bean
    public AuthenticationManager authenticationManagercustom() {
        MyProvider p=new MyProvider(memberService);

        return new ProviderManager(p);
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){

        return new EntryPointHandler();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt Encoder 사용
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    /*@Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtUtill);
    }*/





}



