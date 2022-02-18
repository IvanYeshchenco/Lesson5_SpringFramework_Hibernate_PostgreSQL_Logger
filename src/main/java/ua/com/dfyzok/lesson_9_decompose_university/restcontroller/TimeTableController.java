package ua.com.dfyzok.lesson_9_decompose_university.restcontroller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ua.com.dfyzok.lesson_9_decompose_university.entity.Lesson;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.GroupNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.exceptions.TeacherNotFoundException;
import ua.com.dfyzok.lesson_9_decompose_university.service.TimeTableService;

@Api(value = "TimeTable")
@RestController("TimeTableRestController")
@RequestMapping("/timetable")
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @ApiOperation(value = "Timetable student", notes = "Get timetable for student")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Table not found") })
    @GetMapping("/searchtimetablestudent/{dataBegin}/{dataEnd}/{id}")
    public List<Lesson> timeTableStudent(
            @ApiParam(value = "Start date", name = "dataBegin") @RequestParam @DateTimeFormat(iso = ISO.DATE) String dataBegin,
            @ApiParam(value = "End date", name = "dataEnd") @RequestParam @DateTimeFormat(iso = ISO.DATE) String dataEnd,
            @ApiParam(value = "Student Id", name = "id") @RequestParam Long id)
            throws SQLException, GroupNotFoundException {
        return timeTableService.fetchTimeTableStudent(dataBegin, dataEnd, id);
    }

    @ApiOperation(value = "Timetable teacher", notes = "Get timetable for teacher")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 404, message = "Table not found") })
    @GetMapping("/searchtimetableteachet/{dataBegin}/{dataEnd}/{id}")
    public List<Lesson> timeTableTeacher(
            @ApiParam(value = "Start date", name = "dataBegin") @RequestPart("databegin") @DateTimeFormat(iso = ISO.DATE) String dataBegin,
            @ApiParam(value = "End date", name = "dataEnd") @RequestPart("dataend") @DateTimeFormat(iso = ISO.DATE) String dataEnd,
            @ApiParam(value = "Teacher Id", name = "id") @PathVariable Long id)
            throws SQLException, TeacherNotFoundException {
        return timeTableService.fetchTimeTableTeacher(dataBegin, dataEnd, id);
    }

}
